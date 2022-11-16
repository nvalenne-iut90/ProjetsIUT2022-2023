const {Pool} = require("pg");
const Promise = require("bluebird");
require("dotenv").config();

class AppDAO{
    constructor() {
        const credentials = {
            user : process.env.DB_USERNAME,
            host : process.env.DB_HOST,
            database : process.env.DB_DATABASE,
            password : process.env.DB_PASSWORD,
            port : process.env.DB_PORT,
        };
        this.db = new Pool(credentials);
        this.testConn();
    }

    async testConn(){
        const client = await this.db.connect();
        try {
            const res = await client.query("SELECT current_database()");
            console.log(res.rows[0]);
        } catch (err) {
            console.log("Impossible de se connecter à la base de données", err);
        } finally {
            client.release();
        }
    }

    async run(sql, success_message = "OK"){
        const client = await this.db.connect();
        try {
            await client.query(sql);
            console.log(success_message);
        } catch (err) {
            console.log(err);
        } finally {
            client.release();
        }
    }
    get(sql, params = []){
        return new Promise((resolve, reject) => {
            this.db.query(sql, params, (err, result) => {
                if (err){
                    console.log("Erreur d'éxecution de sql " + sql);
                    console.log(err);
                    reject(err);
                } else {
                    resolve(result.rows[0]);    // Retourner une ligne
                }
            })
        });
    }

    async save(sql, params=[]){
        const client = await this.db.connect();
        try {
            const res = await client.query(sql + ' RETURNING *', params);
            console.log('INSERTED : ' + res.rows[0]);
        } catch (err) {
            console.log(err);
        } finally {
            client.release();
        }
    }

}

module.exports = AppDAO;