import {sequelize} from "./db.config.js";
import {DataTypes, Model} from "sequelize";

export class EntrepriseModel extends Model {}

EntrepriseModel.init({
    // Model attributes are defined here
    id_entreprise : {
        type : DataTypes.INTEGER,
        allowNull : false, // allowNull defaults to true
        primaryKey : true,
        //autoIncrement : true,
    },
    nom_entreprise : {
        type : DataTypes.STRING,
        allowNull: false,
    }
}, {
    sequelize, // We need to pass the connection instance
    tableName: 'entreprise',
    timestamps: false
    // modelName : 'Entreprise' => We can choose the model name

});
