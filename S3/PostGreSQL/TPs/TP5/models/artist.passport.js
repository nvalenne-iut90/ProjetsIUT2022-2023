import sequelize from "../database/connect.js"
import DataTypes from "sequelize";

const PassPort = sequelize.define('passport', {
    id: {
        field: 'PassportId',
        type: DataTypes.UUID,
        primaryKey: true,
        defaultValue: DataTypes.UUIDV1
    },
    code: {
        field: 'Code',
        type: DataTypes.STRING
    },
    issueDate: {
        field: 'IssueDate',
        type: DataTypes.DATEONLY
    },
    expiryDate: {
        field: 'ExpiryDate',
        type: DataTypes.DATEONLY
    }
}, {
    timestamps: false
});

export default PassPort;