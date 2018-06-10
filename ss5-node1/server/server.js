var products= [
    {
        id: 2,
        name: 'Iphone 7',
        price: 10000,
        isAvailable: true
    },
    {
        id: 5,
        name: 'Galaxy s9',
        price: 500,
        isAvailable: false
    },
    {
        id: 4,
        name: 'Iphone 6',
        price: 700,
        isAvailable: true
    },
    {
        id: 6,
        name: 'Nokia 8',
        price: 600,
        isAvailable: false
    }
];

const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app
    .use(bodyParser.json()) // Execute every single request
    .use(bodyParser.urlencoded({extended: true}));

app.get('/api/v1/products', function(req, res){
    res.status(200).json(products);
});

app.get('/api/v1/products/:id', function(req, res){
    const id = parseInt(req.params.id);
    const index = findProductById(id);
    if (index !== -1) {
        res.status(200).json(products[index]);
    }
});

app.post('/api/v1/products', function(req, res){
    const data = req.body;
    data.id = products.length + 1;
    createNewId(data);
    products.push(data);
    res.status(200).json(data);
});

function createNewId(data) {
    if (findProductById(data.id) === -1) {
        return;
    } else {
        data.id ++;
        createNewId(data);
    }
}

app.put('/api/v1/products/:id', function(req, res){
    const id = parseInt(req.params.id);
    const data = req.body;
    data.id = id;
    const index = findProductById(id);
    if (index !== -1) {
        products[index] = data;
        res.status(200).json(data);
    }
});

app.delete('/api/v1/products/:id', function(req, res){
    const id = parseInt(req.params.id);
    const index = findProductById(id);
    if (index !== -1) {
        res.status(200).json(products[index]);
        products.splice(index, 1);
    }});

function findProductById (id) {
    for (var i = 0; i < products.length; i++) {
        if (products[i].id === id) {
            return i;
        }
    }
    return -1;
}

app.listen(3000, function (){
    console.log('Example app listening on port 3000!')
});