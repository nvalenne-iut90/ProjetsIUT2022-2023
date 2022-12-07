import sequelize from "../database/connect.js"
import DataType from "sequelize";

const Artist = sequelize.define('artist', {
    id: {
        field: 'ArtistId',
        type: DataType.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        field: 'Name',
        type: DataType.STRING,
    }
}, {
    tableName: "Artist",
    timestamps: false
});

export default Artist;