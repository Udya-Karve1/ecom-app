const Eureka = require('eureka-js-client').Eureka
const express = require('express')

const bodyParser = require('body-parser')
const cors = require('cors')
const { onShutdown } = require('node-graceful-shutdown')
const port = 3000

const randomNumber = require('uuid')



const app = express()

app.use(cors())

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.get('/health', (req, res) => {
  res.send({ "status":"UP"})
})

app.get('/hello/:me', (req, res) => {
  res.send('Hello ' + req.params.me + '!')
})


const client = new Eureka({
  // application instance information
  "instance": {
    "hostName": "localhost",
    "app": "NOTIFICATION-SERVICE",
    "vipAddress": "notification-service",
    "instanceId": "unique-instance-id",
    "ipAddr": "127.0.0.1",
    "status": "UP",
    "port": {
        "$": 3000,
        "@enabled": true
    },
    "dataCenterInfo": {
        "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
        "name": "MyOwn"
    }
  },
  eureka:{
    host: 'localhost',
    port: 8761,
    serviceUrls:{
      defaults: [
        'http://localhost:8761/eureka/',
        'http://localhost:8762/eureka/'
      ] 
    }
  }
});

const server = app.listen(port, ()=> {
  console.log('notification-service up and listen on port: 3000');
  client.start();
})

process.on('SIGTERM', () => {
  debug('SIGTERM signal received: closing HTTP server')
  console.log('#########################');
  server.close(() => {
    debug('HTTP server closed')
  })
})

onShutdown("http-server", async function () {
  conslog.log("shut down called ******************* ");
  client.stop();  
});

app.get('/disconnect', (req, res) => {
  client.stop();
})

app.get('/connect', (req, res) => {
  client.start();
})
