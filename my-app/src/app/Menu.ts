
import {Restaurant} from "./Restaurant";
import {Meal} from "./Meal"

export class Menu {
  id : number[];
  price : number;
  name : string;
  restaurant : String[];
  meals : Meal[];
  numberOfCommand : number;
}