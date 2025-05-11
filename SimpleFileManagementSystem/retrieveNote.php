<?php
    include("conn.php");
    $allNoteQuery = mysqli_query($conn, "SELECT note_id AS id, title, content, date FROM note ORDER BY date DESC");    
    $notes = [];
    while($row = $allNoteQuery->fetch_assoc()) { // Corrected variable name
        $notes[] = $row;
    }
    echo json_encode($notes);
?>