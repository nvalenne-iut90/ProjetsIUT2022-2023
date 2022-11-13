import express from "express";
import {listAllEntreprises} from "../controllers/entreprise.controller.js";
const router_entreprise = express.Router();

router_entreprise.get("/", listAllEntreprises);

export default router_entreprise;