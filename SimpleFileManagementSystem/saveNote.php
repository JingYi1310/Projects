<?php 
    $data = json_decode(file_get_contents("php://input"), true);
    include("conn.php");

    $id = $conn->real_escape_string($data['id']);
    $title = $conn->real_escape_string($data['title']);
    $content = $conn->real_escape_string($data['content']);

    if ($conn->query("UPDATE note SET title='$title', content='$content' WHERE note_id=$id")) { // Corrected column name
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['success' => false, 'error' => $conn->error]);
    }
?>