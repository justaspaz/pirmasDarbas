package lt.vu.rest;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pc;
import lt.vu.persistence.PCDAO;
import lt.vu.rest.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
@Path("/pc")
public class PcController {
    @Inject
    @Setter
    @Getter
    private PCDAO pcDAO;
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getALl() {
        List<Pc> pcs = pcDAO.loadAll();
        if (pcs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<PcDto> pcDtos = pcs.stream()
                .map(Mapper::convertToGunDto)
                .collect(Collectors.toList());
        return Response.ok(pcDtos).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Pc pc = pcDAO.findOne(id);
        if (pc == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(Mapper.convertToGunDto(pc)).build();
    }

    @Path("/put/{id}/{pcName}/{brand}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer pcId,
            @PathParam("pcName") final String pcName,
            @PathParam("brand") final String brand) {
        try {
            Pc existingPc = pcDAO.findOne(pcId);
            if (existingPc == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingPc.setPcName(pcName);
            existingPc.setBrand(brand);
            pcDAO.update(existingPc);
            return Response.ok(Response.Status.OK).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/post/{pcName}/{brand}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @PathParam("pcName") final String pcName,
            @PathParam("brand") final String brand) {
        Pc pc = new Pc();
        pc.setPcName(pcName);
        pc.setBrand(brand);
        pcDAO.persist(pc);
        return Response.ok(Response.Status.OK).build();
    }
}
