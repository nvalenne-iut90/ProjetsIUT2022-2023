
export default class PrizesMainService {

    /*Requête 1*/
    async getAllLaureates(callback) {
        try {
            let laureates = await prizesRepo.getAllLaureate();
            console.log(laureates);
            return callback(null, laureates.rows);
        } catch(e) {
            console.log(e);
            return callback(e, [])
        }
    }

    /*Requête 2*/
    async ListPrizesFromLaureateID(idLaureate, callback){
        try {
            let laureates = await prizesRepo.ListPrizesFromLaureateID(idLaureate);
            console.log(laureates);
            return callback(null, laureates);
        } catch(e) {
            console.log(e);
            return callback(e, [])
        }
    }

    /*Requête 3*/
    async GetSupOne(callback){
        try {
            let laureates = await prizesRepo.getSupOne();
            console.log(laureates);
            return callback(null, laureates);
        } catch(e) {
            console.log(e);
            return callback(e, [])
        }
    }

    /*Requête 4*/
    async listAllCategories(callback){
        try {
            let categories = await prizesRepo.getAllCategories();
            console.log(categories);
            return callback(null, categories.rows);
        } catch(e) {
            console.log(e);
            return callback(e, [])
        }
    }

    /*Requête 7*/
    async listAllYearsWhereNotPrizes(callback) {
        try {
            let categories = await prizesRepo.getEqualsZero();
            console.log(categories);
            return callback(null, categories);
        } catch(e) {
            console.log(e);
            return callback(e, [])
        }
    }

    async countPrizesForEachPerson(callback){
        
    }


    async updateMotivation(newMotivation, year, id, category, callback){
        
    }

    async deleteInFile(year, id, category, callback){
        
        
    }

    getLaureatesByCategory(category, callback) {
        
    }

    async getPaginatedLaureates(page, limit, callback){
        
    }

    

    async countLaureatesByCategories(callback){
        
    }

    async countLaureatesForEachYear(callback){
        
    }

    sortLaureates(sorting, callback) {
        
    }
    
    async laureateFilter (firstname, surname, category, callback) {

    }

}