//Install express server
const express = require('express');
const path = require('path');
const http = require('http');
const proxy = require('express-http-proxy');

const app = express();

// Serve only the static files form the dist directory
app.use(express.static('./dist/web-ui'));
app.use('/api', proxy('https://backend-git.herokuapp.com/'));

app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', 'https://tim-front2.herokuapp.com');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});

app.get('/*', function (req, res) {
    res.sendFile(path.join(__dirname, '/dist/web-ui/index.html'));
});

const port = process.env.PORT || '8080';
app.set('port', port);

const server = http.createServer(app);

// Start the app by listening on the default Heroku port
server.listen(port, () => console.log(`Running`));