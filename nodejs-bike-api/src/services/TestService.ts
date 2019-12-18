class TestService {
    constructor() {
        console.log('test service');
    }
    getSome(): Array<{number: number,cod: string}>{
        const res: Array<{number: number,cod: string}>= [];
        for (let i = 0; i<100; i++){
            res.push({number: i,cod: i+'ss'})
        }
        return res;
    }
}
module.exports =  new TestService();
