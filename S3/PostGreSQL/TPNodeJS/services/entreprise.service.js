import {EntrepriseModel} from "../models/entreprise.model.js";
export const getAllEntreprises = async (callback) => {
    const entreprises = await EntrepriseModel.findAll();
    //console.log("All entreprises:", JSON.stringify(entreprises, null, 2));
    return callback(null, entreprises);
}