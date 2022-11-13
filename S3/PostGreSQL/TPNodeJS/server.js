import express from "express"
import dotenv from "dotenv";

import {client} from "./models/db.config.js";
import router_entreprise from "./routes/entreprise.router.js";
import router_jury from "./routes/jury.router.js";
import router_prof from "./routes/prof.router.js";
import router_soutenance from "./routes/soutenance.router.js";

dotenv.config();

const app = express();
const port = process.env.NODE_PORT;

app.use("/entreprise", router_entreprise);
app.use("/jury", router_jury);
app.use("/prof", router_prof);
app.use("/soutenance", router_soutenance);

app.listen(port, () => {
    console.log(`Listening on port ${port}`);
    client.connect((err) => {
        if (err) throw err;
        console.log("Database Connected");
    });
});