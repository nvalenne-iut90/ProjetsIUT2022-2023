import { Router } from "express";
import {countLaureates, getAllLaureates, countPrizes, countPrizesForEachPerson, listAllCategories, 
        listAllYearsWhereNotPrizes, ListPrizesFromLaureateID, getSupOne} from "../controllers/prizes_controller.js";

let router_prizes = Router();

router_prizes.get("/", getAllLaureates);    // F1

router_prizes.get("/prizes-from-id/:id", ListPrizesFromLaureateID);    //F2

router_prizes.get("/get-sup-one/", getSupOne);    //F3

router_prizes.get("/list-categories", listAllCategories);              //F4

router_prizes.get("/year-where-not-prizes", listAllYearsWhereNotPrizes);        //F7

//router_prizes.get("/nb-prizes", countPrizes);       //F3
//router_prizes.get("/nb-prizes-per-person", countPrizesForEachPerson);       //F5



export default router_prizes;