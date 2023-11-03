package com.taxislibres.pruebatecnica.infrastructure.Service.ServiceImplement;

import com.taxislibres.pruebatecnica.Domain.Models.Bill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.NewBill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.ShowDataBill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.UpdateBill;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import com.taxislibres.pruebatecnica.infrastructure.Repository.BillRepository;
import com.taxislibres.pruebatecnica.infrastructure.Repository.UserRepository;
import com.taxislibres.pruebatecnica.infrastructure.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de facturas (BillService) que interactúa con el repositorio de facturas.
 */
@Service
public class BillServiceImplement implements BillService {

    @Autowired
    private BillRepository billRepository;
    /**
     * Crea una nueva factura en el sistema a partir de los datos proporcionados en la solicitud.
     *
     * @param newBill Datos de la nueva factura a crear.
     * @return Datos de la factura creada.
     */
    @Override
    public ShowDataBill createBill(NewBill newBill) {
        Bill bill = new Bill();
        bill.setTotalAmount(newBill.getTotalAmount());
        bill.setDescription(newBill.getDescription());
        bill.setUser(newBill.getUser());
        ShowDataBill showData = new ShowDataBill(new Bill());
        return showData;
    }

    /**
     * Actualiza una factura existente en el sistema con los datos proporcionados en la solicitud.
     *
     * @param id         Identificador de la factura a actualizar.
     * @param updateBill Datos actualizados de la factura.
     * @return Datos actualizados de la factura.
     */
    @Override
    public UpdateBill updateBill(Long id, UpdateBill updateBill) {
        Bill billById = billRepository.findById(id).orElseThrow( () ->
                new IllegalStateException("No fue encontrada la factura con el ID: " + id));
        if (billById!= null) {
            if(updateBill.getTotalAmount() != null){
                billById.setTotalAmount(updateBill.getTotalAmount());
            }
            if(updateBill.getDescription() != null){
                billById.setDescription(updateBill.getDescription());
            }
            billRepository.save(billById);
        }
        return updateBill;
    }

    /**
     * Elimina una factura del sistema por su identificador.
     *
     * @param id Identificador de la factura a eliminar.
     */
    @Override
    public void deleteBillById(Long id) {
        Bill billById = billRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("No fue encontrada la factura con el ID: "+ id));
        if(billById != null){
            billRepository.deleteById(id);
        }
    }

    /**
     * Obtiene una lista de todas las facturas en el sistema y las transforma en el formato de datos para mostrar.
     *
     * @return Lista de datos de facturas para mostrar.
     */
    @Override
    public List<ShowDataBill> getAllBills() {
        List<Bill> billList = billRepository.findAll();
        List<ShowDataBill> showDataBills = billList.stream().map(bill -> new ShowDataBill(
                bill.getId(),
                bill.getUser(),
                bill.getTotalAmount(),
                bill.getDescription()
        )).collect(Collectors.toList());
        return showDataBills;
    }

    /**
     * Obtiene una lista de facturas asociadas a un usuario específico en el sistema.
     *
     * @param userId Identificador del usuario para el cual se buscan las facturas.
     * @return Lista de datos de facturas para mostrar asociadas al usuario.
     */
    @Override
    public List<ShowDataBill> getBillByUserId(Long userId) {
        List<Bill> billsByUserId = billRepository.findBillsByUser_Id(userId);
        if(billsByUserId != null){
            List<ShowDataBill> showDataBills = billsByUserId.stream().map( bill -> new ShowDataBill(
                    bill.getId(),
                    bill.getUser(),
                    bill.getTotalAmount(),
                    bill.getDescription()
            )).collect(Collectors.toList());

            return showDataBills;
        }
        return Collections.emptyList();
    }

    /**
     * Obtiene una factura por su identificador en el sistema.
     *
     * @param id Identificador de la factura a obtener.
     * @return Datos de la factura correspondiente al identificador.
     */
    @Override
    public Bill getBillById(Long id) {
        Bill billById = billRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("No fue encontrada la factura con el ID: "+ id));
        return billById;
    }
}
