import sequelize from "./database/connect.js"
import dotenv from 'dotenv';
dotenv.config();
import express from "express";

import Album from "./models/album.js"
import Artist from "./models/artist.js";
import Playlist from "./models/playlist.js";
import Track from "./models/track.js";
import Playlist_Track from "./models/playlist.track.js";
import PassPort from "./models/artist.passport.js";

const app = express();
const port = process.env.PORT;
/**
 * en fait il vérifie la connexion ce grand malade
 */
async function verifyDBCon() {
    try {
        await sequelize.authenticate();
        console.log("Connexion réussie!");
    } catch (error){
        console.error("échec de la connexion");
    }
}

async function syncModels(){
    try {
        await PassPort.sync({force:false})
        await Artist.sync({force:false}) 
        await Playlist.sync({force:false}) 
        await Track.sync({force:false}) 
        await Playlist_Track.sync({force:false})
        await Album.sync({force:false}) 
    }catch (error){
        console.error(error);
    }
}

Playlist.belongsToMany(Track,{
    through: Playlist_Track,
    foreignKey: 'PlaylistId'
});
Track.belongsToMany(Playlist,{
    through: Playlist_Track,
    foreignKey: 'TrackId'
});

Artist.hasOne(PassPort);
PassPort.belongsTo(Artist)


app.listen(port, () => {
    console.log(`Listening on port ${port}`);
    verifyDBCon();
    syncModels();
})