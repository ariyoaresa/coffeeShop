<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Retrieve form data
    $customerName = $_POST['customer_name'];
    $date = $_POST['date'];
    $phoneNumber = $_POST['contact_num'];
    $email = $_POST['email'];
    $description = $_POST['description'];
    $tableNumber = (int)$_POST['table_num'];
    
    // Set the "status" variable to true
    $status = true;


    // Establish a MySQL database connection (replace with your database credentials)
    $conn = new mysqli("localhost", "root", "root", "reservation", 3306);

    // Check for database connection errors
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Prepare the SQL statement to insert data into the reservation_tb table
    $stmt = $conn->prepare("INSERT INTO reservation_tb (customer_name, date, contact_num, email, description, table_num, status) VALUES (?, ?, ?, ?, ?, ?, ?)");

    // Bind parameters and execute the statement
    $stmt->bind_param("sssssi", $customerName, $date, $phoneNumber, $email, $description, $tableNumber, $status);

    if ($stmt->execute()) {
        // Insertion was successful
        echo "Reservation data submitted successfully.";
    } else {
        // Insertion failed
        echo "Failed to submit reservation data.";
    }

    // Close the database connection
    $stmt->close();
    $conn->close();
} else {
    // Request method is not POST
    echo "Invalid request method.";
}
?>