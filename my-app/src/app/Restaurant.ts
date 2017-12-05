enum Price {
  Cheap = "€",
  Average = "€€",
  Expensive = "€€€",
};

enum Food {
  Italian = "Italien",
  Chinese = "Chinois",
  Mexican = "Méxicain",
};

export class Restaurant {
  address : string;
  email : string;
  id : number;
  name : string;
  tel_number : string;
  url_img: string;
  latitude: number;
  longitude: number;
}
