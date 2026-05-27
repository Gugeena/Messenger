function send()
{
    const username = document.getElementById("username").value;
    const message = document.getElementById("message").value;

    fetch('http://localhost:8080/messanger/message',
    {
        method: 'POST',
        headers:
        {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username: username, message: message})
    })
    .then(result => {document.getElementById("username").value = ''; document.getElementById("message").value = ''});
}
function receive()
{
    const username = document.getElementById("name").value;
    const password = document.getElementById("password").value;

    const params = new URLSearchParams({username: username, password: password});

    const url = `http://localhost:8080/messanger/message?${params.toString()}`;
    fetch(url)
    .then(response => response.json())
    .then(result => {document.getElementById("name").value = ''; document.getElementById("password").value = ''; document.getElementById("hidden").innerHTML = result;});
}