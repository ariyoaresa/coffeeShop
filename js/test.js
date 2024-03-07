document
  .getElementById("productForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const jsonObject = {};
    for (const [key, value] of formData.entries()) {
      if (key === "description") {
        jsonObject["discription"] = value;
      } else {
        jsonObject[key] = value;
      }
    }

    fetch("http://localhost:8080/api/v1/products/saveProducts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(jsonObject),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Data sent to the server:", data);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  });
