function register()
{
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch('http://localhost:8080/messanger/user',
    {
        method: 'POST',
        headers:
        {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({user: username, password: password})
    }).then(response => response.json())
    .then(result => {document.getElementById("username").value = ''; document.getElementById("password").value = ''});
}