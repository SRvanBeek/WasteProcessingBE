package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Controller
@RequestMapping(value = "api/cutWaste")
public class CutWasteController {
    private final CutWasteDAO cutWasteDAO;
    private final OrderDAO orderDAO;
    private final VoorraadDAO voorraadDAO;
    private final WasteDAO wasteDAO;

    public CutWasteController(CutWasteDAO cutWasteDAO, OrderDAO orderDAO, VoorraadDAO voorraadDAO, WasteDAO wasteDAO) {
        this.cutWasteDAO = cutWasteDAO;
        this.orderDAO = orderDAO;
        this.voorraadDAO = voorraadDAO;
        this.wasteDAO = wasteDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Cutwaste> getAllCutWaste(){
        return this.cutWasteDAO.getAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postCutWaste(@RequestBody Cutwaste cutwaste){
        this.cutWasteDAO.saveToDatabase(cutwaste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted a CutWaste!");
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Cutwaste> getCutWasteByType(@PathVariable String type){
        return this.cutWasteDAO.getByType(type);
    }

    @RequestMapping(value = "/done/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse setDone(@PathVariable long id, @RequestBody String type){
        Cutwaste cutwaste = this.cutWasteDAO.getById(id);
        cutwaste.setProcessed(true);
        this.cutWasteDAO.saveToDatabase(cutwaste);
        if (Objects.equals(type, "catWaste")){
            Waste waste = this.wasteDAO.getWasteByCutWasteId(id).get();
            waste.setUserId(1);
            waste.setEnabled(true);
            waste.setDateProcessed(new Date());
            this.wasteDAO.saveToDatabase(waste);
        } else if (Objects.equals(type, "Order")) {
            Order order = this.orderDAO.getOrdersByCutWasteId(id).get();
            order.setUserID(1);
            order.setEnabled(true);
            order.setDateProcessed(new Date());
            this.orderDAO.saveToDatabase(order);
        } else if (Objects.equals(type, "Voorraad")) {
            Voorraad voorraad = this.voorraadDAO.getVoorraadByID(id).get();
            voorraad.setUserID(1);
            voorraad.setEnabled(true);
            voorraad.setDateProcessed(new Date());
            this.voorraadDAO.saveToDatabase(voorraad);
        }
        return new ApiResponse(HttpStatus.ACCEPTED, "setDONE!");
    }
}
