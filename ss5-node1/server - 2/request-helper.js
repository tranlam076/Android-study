const url = 'http://http://5b128003d50a5c0014ef11ed.mockapi.io/api/v1/products';
const request = require('request');
module.exports = {
    getAll: function () {
        request('http://5b128003d50a5c0014ef11ed.mockapi.io/api/v1/products', function (error, response, body) {
            if (!error && response.statusCode == 200) {
                console.log(body);
            }
        });
    },
};