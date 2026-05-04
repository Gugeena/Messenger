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
    })
    .then(() => {document.getElementById("username").value = ''; console.log("tried my best"); document.getElementById("password").value = ''});
}