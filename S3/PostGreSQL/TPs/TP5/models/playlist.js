import sequelize from "../database/connect.js"
import DataType from "sequelize";

const Playlist = sequelize.define('playlist', {
    id: {
        field: 'PlaylistId',
        type: DataType.INTEGER,
        primaryKey: true
    },
    name: {
        field : 'Name',
        type: DataType.STRING
    }
}, {
    tableName: "Playlist",
    timestamps: false
});

export default Playlist;