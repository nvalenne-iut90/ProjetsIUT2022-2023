import sequelize from "../database/connect.js"
import DataType from "sequelize";

const Track = sequelize.define('track', {
    id: {
        field: 'TrackId',
        type: DataType.INTEGER,
        primaryKey: true
    },
    name: {
        field : 'Name',
        type: DataType.STRING
    }
}, {
    tableName: "Track",
    timestamps: false
})

export default Track;