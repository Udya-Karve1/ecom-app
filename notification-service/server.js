/**
 * 
 *  Kafka consumer object setting pending
 * 
 */

const onShutdown = require('node-graceful-shutdown')

const Eureka = require('eureka-js-client').Eureka
const express = require('express')

const bodyParser = require('body-parser')
const cors = require('cors')
const port = 3000

const client = new Eureka({
    // application instance information
    instance: {
        hostName: 'localhost',
        app: "NOTIFICATION-SERVICE",
        vipAddress: "notification-service",
        instanceId: "unique-instance-id",
        ipAddr: "127.0.0.1",
        status: "UP",
        port: {
            "$": 3000,
            "@enabled": true
        },
        dataCenterInfo: {
            "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
            "name": "MyOwn"
        }
    },
    eureka: {      
      host: 'localhost',
      port: 8761,
      preferSameZone: true,
      registerWithEureka: true,
      serviceUrls: 'http://localhost:8761/eureka/'
    },
  });

  const app = express()

  app.use(cors())
  app.use(bodyParser)

  app.use(bodyParser.urlencoded({ extended: false }));
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

  app.listen(port, ()=> {
    console.log('notification-service up and listen on port: 3000');
    client.start();
  })


onShutdown("http-server", async function () {
    conslog.log("shut down called ******************* ");
    client.stop();
});
  