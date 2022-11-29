import PrizesMainService from "../services/prizes-main.service.js"
let service = new PrizesMainService();

export const countPrizes = (req, res) => {
    service.countPrizes((error, result) => {
        if (error)
            res.status(400).send({success: 0, data: error})
        res.status(200).send(result)
    });
}

export const listAllCategories =  (req, res) => {
    service.listAllCategories((error, categories) => {
        if (error)
            res.status(400).send({success: 0, data: error})
        else
            res.status(200).send(categories)
    });
}

export const getSupOne =  (req, res) => {
    service.GetSupOne((error, result) => {
        if (error)
            res.status(400).send({success: 0, data: error})
        else
            res.status(200).send(result)
    });
}

//TODO Gérer les doublons
export const countPrizesForEachPerson =  (req, res) => {
    service.countPrizesForEachPerson((error, result) => {
        if (error)
            res.status(400).send({success: 0, data: error});
        //console.log(objectsPage)
        res.status(200).send(result)
    });
}

export const listAllYearsWhereNotPrizes = (req, res) => {
    service.listAllYearsWhereNotPrizes((error, result) => {
        if (error)
            res.status(400).send({success: 0, data:error});
        else
            res.status(200).send(result);
    })
}

export const ListPrizesFromLaureateID = (req, res) => {
    const idLaureate = req.params.id;
    service.ListPrizesFromLaureateID(idLaureate, (error, result) => {
        if (error)
            res.status(400).send({success: 0, data:error});
        else
            res.status(200).send(result);
    })
}

export const getAllLaureates = (req, res) => {
    service.getAllLaureates((error, laureates) => {
        if (error)
            res.status(400).send({success: 1, data: error});
        else {
            console.log(laureates);
            res.status(200).send(laureates);
        }
    });
}

export const countLaureates = (req, res) => {
    service.countLaureates((error, result) => {
        //console.log(result);
        res.status(200).send(result.toString())
    });
}

export const countLaureatesByCategories = (req, res) => {
    service.countLaureatesByCategories((error, result) => {
        if (error)
            res.status(400).send({success: 0, data: error});
        else res.status(200).send(result);
    });
}

export const countLaureatesForEachYear = (req, res) => {
    service.countLaureatesForEachYear((error, result) => {
        if (error)
            res.status(400).send({success:0,data:error});
        else
            res.status(200).send(result);
    })
}

export const sortLaureates = (req, res) => {
    let sorting = req.query.sorting;
    service.sortLaureates(sorting, (error, result) => {
        if (error)
            res.status(400).send({success:0, data:error})
        else res.status(200).send(result)
    })
}

export const laureateFilter = (req, res) => {
    let firstname = req.query.firstname;
    let surname = req.query.surname;
    let category = req.query.category;
    service.laureateFilter(firstname, surname, category, (error, result) => {
        if (error)
            res.status(400).send({success:0,data:error});
        res.status(200).send(result);
    })
}

export const updateMotivation = (req, res) => {
    let annee = req.query.year;
    let id = req.query.id;
    let category = req.query.category;
    let motivation = req.query.motivation
    if (annee === undefined || id === undefined || category === undefined || motivation === undefined)
        res.status(400).send({success:0, data:"Missing parameter"});
        service.updateMotivation(motivation, annee, id, category, (error, content) => {
        if (error){
            res.status(400).send(error);
        } else {
            fs.writeFile("prize.json", JSON.stringify(content), (err) => {
                if (err) throw err;
            });
            res.status(200).send("Laureate updated successfully !");
        }
    });
    
}

export const deleteInFile = (req, res) => {
    let annee = req.query.year;
    let id = req.query.id;
    let category = req.query.category;
    if (annee === undefined || id === undefined || category === undefined)
        res.status(400).send({success:0, data:"Missing parameter"});
    //console.log(annee, id, category);
    service.deleteInFile(annee, id, category, (error, content) => {
        if (error){
            res.status(400).send(error)
        } else {
            //console.log(content);
            fs.writeFile("prize.json", JSON.stringify(content), (err) => {
                if (err) throw err;
                console.log("Laureate has been deleted successfully");
            });
            res.status(200).send("Laureate has been deleted successfully");
        }
    });
}