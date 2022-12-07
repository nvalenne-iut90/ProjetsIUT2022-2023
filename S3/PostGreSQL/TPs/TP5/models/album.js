import sequelize from "../database/connect.js"
import DataType from "sequelize";

const Album = sequelize.define('album',{
    id: {
        field: 'AlbumId',
        type: DataType.INTEGER,
        primaryKey: true
    },
    name: {
        field: 'Title',
        type: DataType.STRING
    }
},{
    tableName: "Album",
    timestamps: false
});

export default Album;