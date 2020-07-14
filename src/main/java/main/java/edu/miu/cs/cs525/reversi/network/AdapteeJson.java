package main.java.edu.miu.cs.cs525.reversi.network;

import com.google.gson.Gson;

import main.java.edu.miu.cs.cs525.reversi.utils.Convert;
import main.java.edu.miu.cs.cs525.reversi.utils.ConvertToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AdapteeJson {
	Convert convert = new ConvertToString();

    public boolean isJson(String jsonInString) {

        try {
            new JSONObject(jsonInString);
        } catch (JSONException ex) {

            try {
                new JSONArray(jsonInString);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;


    }

    public String JsonString(String str) {
        JsonData data = new Gson().fromJson(str, JsonData.class);
        //updated for both teams
        String result = "";
//        System.out.println("resTeam3: " + data.getHomeNewPiece().getRow());
          /************Team 3************************************************/
        if (data.getHomeNewPiece().getRow() != null && data.getHomeNewPiece().getCol() != null) {
//          result = util.intToString(data.getHomeNewPiece().getRow()) + (data.getHomeNewPiece().getCol() + 1);
        	result = convert.intToString(data.getHomeNewPiece().getCol()) + (data.getHomeNewPiece().getRow() + 1);

        }

           /*****************Team 2, 5 and 6*********************/
        if (data.getX() != null & data.getY() != null) {
            result = convert.intToString(data.getX()) + (data.getY() + 1);
           /******************use this for Team5 to match to  view********/
           //result = util.intToString(data.getY()) + (data.getX() + 1);
           /****************Team 1**********************************/
        } else if (data.getRow() != null && data.getCol() != null) {
            //changed to make similar view works in both cases
//           result = util.intToString(data.getRow())+(data.getCol()+1);
            result = convert.intToString(data.getCol()) + (data.getRow() + 1);
        }
        return result;

    }


}
