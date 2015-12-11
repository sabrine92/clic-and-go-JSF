package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.MeanOfTransport;

@Local
public interface MeanOfTransportServicesLocal {
	Boolean addMeanOfTransport(MeanOfTransport MeanOfTransport);

	Boolean deleteMeanOfTransportById(String Id);

	Boolean updateMeanOfTransport(MeanOfTransport MeanOfTransport);

	MeanOfTransport findMeanOfTransportById(
			String Id);

	Boolean deleteMeanOfTransport(MeanOfTransport MeanOfTransport);

	List<MeanOfTransport> findAllMeanOfTransports();
}
