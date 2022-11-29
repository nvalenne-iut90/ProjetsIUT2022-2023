// Modules npm
import express from "express";
import {engine} from "express-handlebars";
import {dirname} from "path";
import dotenv from 'dotenv';
import bodyParser from "body-parser";
import {fileURLToPath} from "url";
import {AppDAO} from "./dao.js";
import PrizesRepository from "./repository/prizes_repository.js";
global.dao = new AppDAO();
global.prizesRepo = new PrizesRepository(dao);

// Router files
import {default as router_prizes} from "./routes/prizes_router.js";

dotenv.config();

const port = process.env.NODE_PORT;
const app = express();
const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
app.use(bodyParser.urlencoded({ extended: true }));

app.engine('hbs', engine({
    defaultLayout : 'main',
    extname : 'hbs'
}));

app.use("/prizes", router_prizes);



app.listen(port, async () => {
    console.log(`Listening on port ${port}` );
    prizesRepo.dropData().then(()=>{
        prizesRepo.createTable().then(()=>{
            return prizesRepo.countPrizes();
        }).then(async (numrows) => {
            if(numrows == 0){
                await prizesRepo.initTable();
                await prizesRepo.initTablePrizes();
                await prizesRepo.initTableLaureate();
            }
        }).then(() => {
            return prizesRepo.countPrizes();
        }).then((numrows) => {
            console.log(`Prizes : ${numrows}`);
        }).then(() => {
            return prizesRepo.countLaureate();
        }).then((numrows) => {
            console.log(`Laureate : ${numrows}`);
        }).catch((err)=>{
            console.log(err);
        })
    })
});