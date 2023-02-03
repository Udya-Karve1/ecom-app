const { Kafka, logLevel } = require("kafkajs")
const nodeemailer = require('nodemailer');

const clientId = "notification-service"
const brokers = ["localhost:9092"]
const topic = "email-topic"

const kafka = new Kafka({
	clientId,
	brokers,
	logLevel: logLevel.DEBUG,
})

const emailConfig = {
    from: "uday.karve@softwebsolutions.com",
    to: "udaykarve77@gmail.com",
    subject: "ecom-notification",
    text: "This is test email"
};

var tranporter = nodeemailer.createTransport({
    service: "outlook",
    auth:{
        user: 'uday.karve@softwebsolutions.com',
        password: 'Shrirushi#123'
    }
})

const consumer = kafka.consumer({
	groupId: clientId,
	minBytes: 5,
	maxBytes: 1e6,
	maxWaitTimeInMs: 3000,
})

const consume = async () => {
	await consumer.connect()
	await consumer.subscribe({ topic, fromBeginning: true })
	await consumer.run({
		eachMessage: ({ message }) => {
			console.log(`received message: ${message.value}`);
            transporter.sendMail(mailOptions, function(error, info){
                if (error) {
                    console.log(error);
                } else {
                    console.log('Email sent: ' + info.response);
                }
            });            
		},
	})
}


module.exports = consume