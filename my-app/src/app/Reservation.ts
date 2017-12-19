import { Restaurant } from "./Restaurant";

export class Reservation {
  id:number;
  nbCouverts:number;
  periode:String;
  date:Date;
  restaurant :Restaurant;
}