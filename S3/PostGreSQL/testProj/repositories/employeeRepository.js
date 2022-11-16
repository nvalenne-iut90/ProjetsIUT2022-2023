class EmployeeRepository{
    constructor(dao) {
        this.dao = dao;
    }
    async createTable(){
        const sql = `
            CREATE TABLE IF NOT EXISTS employee (
                employee_id INT PRIMARY KEY,
                first_name VARCHAR(255) NOT NULL,
                last_name VARCHAR(255) NOT NULL,
                manager_id INT,
                job_info VARCHAR(50),
                FOREIGN KEY(manager_id) REFERENCES employee(employee_id) ON DELETE CASCADE
            )
        `;
        return this.dao.run(sql, "Employee table OK !");
    }

    async count() {
        let sql = `SELECT COUNT(*) FROM employee`;
        const res = this.dao.get(sql);
        return res.count;

    }

    async initTable(){
        const data = [
            [1, 'Hugo', 'FOULON', null, 'CEO'],
            [2, 'David', 'MONNIER', 1, 'FRONT-END MANAGER'],
            [3, 'Ayoub', 'TAGUIA', 1, 'BACK-END MANAGER'],
            [4, 'Nathan', 'VALENNE', 2, 'FRONT-END DEV'],
            [5, 'Arnaud', 'CHEVALME', 2, 'FRONT-END DEV'],
            [6, 'Lucas', 'DUBOL', 3, 'BACK-END DEV'],
            [7, 'Arnaud', 'CHEVALME', 3, 'BACK-END DEV'],
        ]
    }

}
module.exports = EmployeeRepository;