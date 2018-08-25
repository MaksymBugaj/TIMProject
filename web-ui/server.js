//Install express server
const express = require('express');
const path = require('path');
const http = require('http');
const proxy = require('express-http-proxy');

const app = express();

// Serve only the static files form the dist directory
app.use(express.static('./dist/web-ui'));
app.use('/api', proxy(''));

app.get('/*', function (req, res) {
    res.sendFile(path.join(__dirname, '/dist/web-ui/index.html'));
});

const port = process.env.PORT || '8080';
app.set('port', port);

const server = http.createServer(app);

// Start the app by listening on the default Heroku port
server.listen(port, () => console.log(`Running`));