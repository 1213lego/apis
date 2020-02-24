import * as dotenv from 'dotenv';
import express, {Request, Response} from 'express';
import cors, {CorsOptions} from 'cors';
import helmet from 'helmet';
import morgan from 'morgan';

dotenv.config();
if (!process.env.PORT) {
    process.exit(1);
}
const logger = morgan('dev');
const PORT: number = parseInt(process.env.PORT as string, 10);
const app = express();
const corsOptions: CorsOptions = {
    origin: '*',
    methods: '*'
};
app.use(logger);
app.use(helmet());
app.use(cors(corsOptions));
app.use(express.json());
app.get('/', (req: Request, res: Response) => {
    res.send({message: 'API UP'});
});

const server = app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}`);
});