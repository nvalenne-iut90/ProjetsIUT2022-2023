import Sequelize from 'sequelize';
import dotenv from 'dotenv';
dotenv.config();

const sequelize = new Sequelize(process.env.DB_DATABASE, process.env.DB_USER, process.env.DB_PASSWORD,{
    dialect: 'postgres',
        port: 5432,
        host: process.env.DB_HOST
})

export default sequelize;
