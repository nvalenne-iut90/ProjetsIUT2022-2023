import sequelize from "../database/connect.js"

const Playlist_Track = sequelize.define('playlist_track', 
    {},{
        tableName: "PlaylistTrack",
        timestamps: false
    })

export default Playlist_Track;