const express = require('express');
const app = express();
const Joi = require('joi');

app.use(express.json());

app.get('/', (req, res) => {
    res.send(
        'Paths:' +
        '/api/sensors - to display all sensors;' +
        '/api/sensors/:id - to display the sensor with specified id;');
});

//
// Queries and validation for Sensor
//

const sensors = [
    { id: 1, name: 'Temperature Sensor', room: 1 },
    { id: 2, name: 'Electricity Sensor', room: 1 },
    { id: 3, name: 'Temperature Sensor', room: 2 }
];

app.get('/api/sensors', (req, res) => {
    res.send(sensors);
});

app.get('/api/sensors/:id', (req, res) => {
    const sensor = sensors.find(s => s.id === parseInt(req.params.id));
    if(!sensor) return res.status(404).send("The sensor with given ID wasn't found");
    res.send(sensor);
});

app.post('/api/sensors', (req, res) => {
    const { error } = validateSensor(req.body);

    if(error) return res.status(400).send(error.details[0].message);

    const sensor = {
        id: sensors.length + 1,
        name: req.body.name,
        room: req.body.room
    };

    sensors.push(sensor);
    res.send(sensor);
});

app.put('/api/sensors/:id', (req, res) => {
    const sensor = sensors.find(s => s.id === parseInt(req.params.id));
    if(!sensor) return res.status(404).send("The sensor with given ID wasn't found");

    const { error } = validateSensor(req.body);

    if(error) return res.status(400).send(error.details[0].message);

    sensor.name = req.body.name;
    sensor.room = req.body.room;
    res.send(sensor);
});

app.del('/api/sensors/:id', (req, res) => {
    const sensor = sensors.find(s => s.id === parseInt(req.params.id));
    if(!sensor) return res.status(404).send("The sensor with given ID wasn't found");

    const index = sensors.indexOf(sensor);
    sensors.splice(index, 1);

    res.send(sensor);
});

function validateSensor(sensor) {
    const schema = {
        name: Joi.string().min(3).required(),
        room: Joi.string().min(3).required()
    };

    return Joi.validate(sensor, schema);
}

//
// port settings
//

const port = process.env.PORT || 5000;
app.listen(port, () => console.log(`Listening on port ${port}...`));
