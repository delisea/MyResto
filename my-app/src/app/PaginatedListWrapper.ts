import {Restaurant} from "./Restaurant";
export class PaginatedListWrapper{
    // private Integer currentPage;
    // private Integer pageSize;
    // private Integer totalResults;

    // private String sortFields;
    // private String sortDirections;
    
    // private List<Restaurant> restaurants;

    currentPage: number;
    pageSize: number;
    totalResults: number;
    sortFields: string;
    sortDirections:string;
    restaurants : Restaurant[];
}