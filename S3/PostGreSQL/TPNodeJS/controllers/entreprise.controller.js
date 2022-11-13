import {getAllEntreprises} from "../services/entreprise.service.js";

export const listAllEntreprises = async (req, res) => {
    await getAllEntreprises((error, result) => {
        if (error) res.status(400).send({success:1, data:error})
        else res.status(200).send({success:0, data:result});
    })
}