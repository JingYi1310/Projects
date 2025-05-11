<?php 
$data = json_decode(file_get_contents("php://input"), true);
include("conn.php");

$id = $conn->real_escape_string($data['id']);
if ($conn->query("DELETE FROM note WHERE note_id=$id")) { // Corrected column name
    echo json_encode(['success' => true]);
} else {
    echo json_encode(['success' => false, 'error' => $conn->error]);
}
?>