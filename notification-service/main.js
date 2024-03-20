const request = require('request');
const ip = require('ip');

const eurekaService = `http://localhost:8761/eureka`;

const Eureka = require('eureka-js-client').Eureka
const express = require('express')

const bodyParser = require('body-parser')
const cors = require('cors')
const { onShutdown } = require('node-graceful-shutdown')
const port = 3000

const randomNumber = require('uuid')

module.exports = {
   registerWithEureka: (appName, port) => {
       console.log(`Registering ${appName} with Eureka`);
       request.post({
           headers: {'content-type': 'application/json'},
           url: `${eurekaService}/apps/${appName}`,
           body: JSON.stringify({
               instance: {
                   hostName: `localhost`,
                   instanceId: `${appName}-${port}`,
                   vipAddress: `${appName}`,
                   app: `${appName.toUpperCase()}`,
                   ipAddr: ip.address(),
                   status: `UP`,
                   port: {
                       $: port,
                       "@enabled": true
                   },
                   dataCenterInfo: {
                       "@class": `com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo`,
                       name: `MyOwn`
                   }
               }
           })
       },
       (error, response, body) => {
           if(!error) {
               console.log(`Registered with Eureka.`);
               setInterval(() => {
                   request.put({
                       headers: {'content-type': 'application/json'},
                       url: `${eurekaService}/apps/${appName}/${appName}-${port}`
                   }, (error, response, body => {
                       if (error) {
                           console.log('Sending heartbeat to Eureka failed.');
                       } else {
                           console.log('Successfully sent heartbeat to Eureka.');
                       }
                   }));
               }, 50 * 1000);
      
           } else {
               console.log(`Not registered with eureka due to: ${error}`);
           }
       });
   }
};


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


//require('../eureka-helper/eureka-helper').registerWithEureka('notification-service', port);
registerWithEureka('notification-service', port);


console.log(`Threats service listening on port ${port}`);
app.listen(port);