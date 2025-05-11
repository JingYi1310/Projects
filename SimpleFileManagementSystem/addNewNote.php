<?php
    include("conn.php");

    $query = "INSERT INTO note(title, content, date) VALUES ('New Note', '', NOW())";
    if (mysqli_query($conn, $query)) {
        $newNoteId = $conn->insert_id; // Get the ID of the newly inserted note
        $newNoteQuery = mysqli_query($conn, "SELECT note_id AS id, title, content, date FROM note WHERE note_id=$newNoteId");
        $newNote = $newNoteQuery->fetch_assoc();
        echo json_encode(['success' => true, 'note' => $newNote]);
    } else {
        echo json_encode(['success' => false, 'error' => $conn->error]);
    }
?>