const express = require("express");
const app = express();
require('dotenv').config();
const bodyParser = require('body-parser');
const port = process.env.NODE_PORT;
const AppDAO = require("./dao");
const EmployeeRepository = require("./repositories/employeeRepository");

global.dao = new AppDAO();
global.employeeRepo = new EmployeeRepository(dao);

app.use(bodyParser.json());
app.listen(port, () => {
    console.log(`Le serveur écoute sur le port ${port}`);
    employeeRepo.createTable()
        .then(() => {
            return employeeRepo.count();
        })
        .then((numRows) => {
            console.log(`num rows in employees: ${numRows}`);
            if (numRows === 0) return employeeRepo.initTable();
        })
        .catch((err) => {
            console.log(err);
        })
});
