import pg from "pg";
import Promise from "bluebird";
import dotenv from 'dotenv';
dotenv.config();

export class AppDAO{
    constructor() {
        const credentials = {
            user: process.env.DB_USERNAME,
            host: process.env.DB_HOST,
            database: process.env.DB_DATABASE,
            password: process.env.DB_PASSWORD,
            port: process.env.DB_PORT,
        };
        this.db = new pg.Pool(credentials);
        this.testConn();
    }
    async testConn(){
        const client = await this.db.connect();
        try {
            const res = await client.query("SELECT current_database()");
            console.log(res.rows[0]);
        }catch(err){
            console.log("Impossible de se connecter à la base de données", err);
        }finally {
            client.release();
        }
    }

    async run(sql, success_message = "OK"){
        const client = await this.db.connect();
        try {
            await client.query(sql);
            console.log(success_message);
        }catch(err){
            console.log(err);
        }finally {
            client.release();
        }
    }

    get(sql, params = []){
        return new Promise((resolve, reject) => {
            this.db.query(sql, params, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result.rows[0]); //retourne une ligne
                }
            })
        })
    }

    getAllCategories(sql){
        return new Promise((resolve, reject) => {
            this.db.query(sql, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result); //retourne une ligne
                }
            })
        })
    }

    getSupOne(sql){
        return new Promise((resolve, reject) => {
            this.db.query(sql, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result); //retourne une ligne
                }
            })
        })
    }

    getEqualsZero(sql){
        return new Promise((resolve, reject) => {
            this.db.query(sql, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result.rows); //retourne une ligne
                }
            })
        })
    }

    async save(sql,params=[]){
        const client = await this.db.connect();
        try {
            const res = await client.query(sql + ' RETURNING *', params);
            //console.log('INSERTED : ' + res.rows[0]);
        }catch(err){
            console.log(err);
        }finally {
            client.release();
        }
    }

    all(sql, params = []){
        return new Promise((resolve, reject) => {
            this.db.query(sql, params, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result.rows);
                }
            })
        })
    }

    allLaureates(sql){
        return new Promise((resolve, reject) => {
            this.db.query(sql, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result);
                }
            })
        })
    }

    async delete(sql,params=[]){
        const client = await this.db.connect();
        try {
            const res = await client.query('WITH deleted AS (' + sql + ' RETURNING *)' + 'SELECT count(*) FROM deleted;', params);
            console.log('DELETED ROWS : ' + res.rows[0].count);
            return res.rows[0].count + ' DELETED RECORDS';
        }catch(err){
            console.log(err);
        }finally {
            client.release();
        }
    }

    async drop(sql){
        const client = await this.db.connect();
        try {
            const res = await client.query(sql);
            console.log('DROP TABLES');
            return 'DROP TABLES';
        }catch(err){
            console.log(err);
        }finally {
            client.release();
        }
    }

    getIdCategory(sql, params = []){
        return new Promise((resolve, reject) => {
            this.db.query(sql, params, (err, result) => {
                if(err) {
                    console.log("Erreur d'execution de sql " + sql);
                    console.log(err);
                    reject(err);
                }
                else{
                    resolve(result.rows); //retourne une ligne
                }
            })
        })
    }

}

export default AppDAO;