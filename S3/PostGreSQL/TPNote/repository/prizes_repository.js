import fs from 'fs';
class prizesRepository{
    constructor(dao) {
        this.dao = dao;
    }

    async dropData(){
        let sql = 'DROP TABLE IF EXISTS laureate; DROP TABLE IF EXISTS prizes; DROP TABLE IF EXISTS category; ';
        let res = await this.dao.drop(sql);
        return res;
    }

    async createTable(){
        let sql = `CREATE TABLE IF NOT EXISTS category ( category_id INT PRIMARY KEY, category VARCHAR(50) NOT NULL);`
        sql += `CREATE TABLE IF NOT EXISTS prizes ( prize_id INT PRIMARY KEY, year VARCHAR(5) NOT NULL, category_id INT, CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE);`;
        sql += `CREATE TABLE IF NOT EXISTS laureate ( laureate_id INT PRIMARY KEY, id INT, firstname VARCHAR(100), surname VARCHAR(100), motivation VARCHAR(400), share INT, prize_id INT, CONSTRAINT fk_prize_id FOREIGN KEY (prize_id) REFERENCES prizes (prize_id) ON DELETE CASCADE);`;
        return this.dao.run(sql,"Prizes && Laureates tables OK!");
    }

    async initTable(){
        const dataBuffer = fs.readFileSync("prize.json");
        let prizes = JSON.parse(dataBuffer);
        let compteur = 0;
        
        let tabs = [];

        for(const prize of prizes){
            if (!(tabs.includes(prize.category))){
                tabs.push(prize.category);
            }
        };

        for(const val of tabs){
            compteur++;
            let cols = [compteur, val];
            const query = `INSERT INTO category(category_id, category) VALUES ($1, $2)`
            await this.dao.save(query, cols);
        };
    }

    async initTablePrizes(){
        const dataBuffer = fs.readFileSync("prize.json");
        let prizes = JSON.parse(dataBuffer);
        let compteur = 0;
        let tabs = []

        for(const prize of prizes){
            if (!(tabs.includes(prize.category))){
                tabs.push(prize.category);
            }
        };
        
        for(const prize of prizes){
            let id =0;
            for(const cat of tabs){
                if(cat==prize.category){
                    id = tabs.indexOf(prize.category)+1;
                }
            };
            compteur++;
            let cols = [compteur, prize.year, (id)];
            const query = `INSERT INTO prizes(prize_id, year, category_id) VALUES ($1, $2, $3)`
            await this.dao.save(query, cols);
        };
    }

    async initTableLaureate(){
        const dataBuffer = fs.readFileSync("prize.json");
        let prizes = JSON.parse(dataBuffer);
        let compteur = 0;
        let compteur2 = 0;
        for(const prize of prizes){
            compteur++;
            if(prize.laureates != undefined){
                for(const laureate of prize.laureates){
                    compteur2++;
                    let cols = [compteur2, laureate.id, laureate.firstname, laureate.surname, laureate.motivation, laureate.share, compteur];
                    const query = `INSERT INTO laureate VALUES ($1, $2, $3, $4, $5, $6, $7)`
                    await this.dao.save(query, cols);
                };
            }
        };
    }

    /*Requête 1*/
    async getAllLaureate() {
        let sql = 'SELECT (id, firstname, surname) FROM laureate';
        let rows = await this.dao.allLaureates(sql);
        return rows;
    }

    /*Requête 1*/
    async ListPrizesFromLaureateID(id) {
        let sql = 'SELECT (id, firstname, surname) FROM laureate WHERE id=$1';
        let rows = await this.dao.get(sql, id);
        return rows;
    }

    /*Requête 3*/
    async getSupOne(){
        let sql = 'SELECT (firstname, surname, COUNT(prizes.prize_id)) FROM prizes LEFT JOIN laureate ON laureate.prize_id=prizes.prize_id GROUP BY firstname, surname HAVING COUNT(prizes.prize_id)>=2';
        let res = await this.dao.all(sql);
        return res;
    }

    /*Requête 4*/
    async getAllCategories(){
        let sql = 'SELECT category FROM category';
        let res = await this.dao.getAllCategories(sql);
        return res;
    }

    /*Requête 7*/
    async getEqualsZero(){
        let sql = 'SELECT (year) FROM prizes LEFT JOIN laureate ON laureate.prize_id=prizes.prize_id GROUP BY year HAVING COUNT(id)<=0';
        let res = await this.dao.getEqualsZero(sql);
        return res;
    }

    


    async countPrizes(){
        let sql = `SELECT COUNT(*) FROM prizes`;
        let res = await this.dao.get(sql);
        return res.count;
    }

    async countLaureate(){
        let sql = `SELECT COUNT(*) FROM laureate`;
        let res = await this.dao.get(sql);
        return res.count;
    }
    
}

export default prizesRepository