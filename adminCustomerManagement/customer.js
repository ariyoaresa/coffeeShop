getAllCustomers();
function saveCustomer(){
    let email=$('#exampleFormControlInput2').val();
    let password=$('#exampleFormControlInput3').val();
    let firstName=$('#exampleFormControlInput4').val();
    let lastName=$('#exampleFormControlInput5').val();
    let address=$('#exampleFormControlInput6').val();
    let telephone=$('#exampleFormControlInput7').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8082/api/v1/admin/saveCustomers",
        async:"true",
        data:JSON.stringify({

            "id": "",
            "email": email,
            "password": password,
            "firstName": firstName,
            "lastName": lastName,
            "address": address,
            "telephone": telephone
        }),
        success: function (data){
            alert("saved")
            getAllCustomers()
        },
        error: function (xhr,exception){
            alert("Error")
        }
    })
}

function updateCustomer(){
    let cusId=$('#exampleFormControlInput1').val();
    let email=$('#exampleFormControlInput2').val();
    let password=$('#exampleFormControlInput3').val();
    let firstName=$('#exampleFormControlInput4').val();
    let lastName=$('#exampleFormControlInput5').val();
    let address=$('#exampleFormControlInput6').val();
    let telephone=$('#exampleFormControlInput7').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8082/api/v1/admin/"+cusId,
        async:"true",
        data:JSON.stringify({

            "id": cusId,
            "email": email,
            "password": password,
            "firstName": firstName,
            "lastName": lastName,
            "address": address,
            "telephone": telephone
        }),
        success: function (data){
            alert("updated")
            getAllCustomers()
        },
        error: function (xhr,exception){
            alert("Error")
        }
    })
}

function deleteCustomer(){
    let cusId=$('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8082/api/v1/admin/"+cusId,
        async:"true",

        success: function (data){
            alert("Deleted")
            getAllCustomers()
        },
        error: function (xhr,exception){
            alert("Error")
        }
    })
}
function getAllCustomers() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8082/api/v1/admin/getCustomers",
        async: true,
        success: function (data) {
            $('#cusTable').empty();
            if (Array.isArray(data)) {
                for (let cus of data) {
                    let cusId = cus.id;
                    let email = cus.email;
                    let password = cus.password;
                    let firstName = cus.firstName;
                    let lastName = cus.lastName;
                    let address = cus.address;
                    let telephone = cus.telephone;

                    var row = '<tr><td>' + cusId + '</td><td>' + email + '</td><td>' + password + '</td><td>' + firstName + '</td><td>' + lastName + '</td><td>' + address + '</td><td>' + telephone + '</td></tr>';
                    $('#cusTable').append(row);
                }
            } else {
                console.log("Data format is not as expected:", data);
            }
        },
        error: function (xhr, exception) {
            console.log("Error:", xhr.responseText);
        }
    });
}
$(document).ready(function () {
    $('#cusTable').on('click', 'tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();
        var col6 = $(this).find('td:eq(6)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);
        $('#exampleFormControlInput5').val(col4);
        $('#exampleFormControlInput6').val(col5);
        $('#exampleFormControlInput7').val(col6);
    });
});

