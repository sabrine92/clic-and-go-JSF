package beans;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
 








import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

import entities.Line;
import entities.Station;
import services.interfaces.LineServicesLocal;
import services.interfaces.StationLineManagementLocal;
import services.interfaces.StationServicesLocal;
 
@ManagedBean
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BubbleChartModel bubbleModel1;
    private BubbleChartModel bubbleModel2;
    @EJB
    private StationLineManagementLocal stationLineManagementLocal;
    private List<Station> stations;
    private List<Line> lines;
    
    
 
    
    
   

	

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public StationLineManagementLocal getStationLineManagementLocal() {
		return stationLineManagementLocal;
	}

	public void setStationLineManagementLocal(
			StationLineManagementLocal stationLineManagementLocal) {
		this.stationLineManagementLocal = stationLineManagementLocal;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	@PostConstruct
    public void init() {
        createBubbleModels();
    }
 
    public BubbleChartModel getBubbleModel1() {
        return bubbleModel1;
    }
     
    public BubbleChartModel getBubbleModel2() {
        return bubbleModel2;
    }
         
    private void createBubbleModels(){
        bubbleModel1 = initBubbleModel();
        bubbleModel1.setTitle("Bubble Chart");
        bubbleModel1.getAxis(AxisType.X).setLabel("Price");
        Axis yAxis = bubbleModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setLabel("Labels");
         
        bubbleModel2 = initBubbleModel();
        bubbleModel2.setTitle("Custom Options");
        bubbleModel2.setShadow(false);
        bubbleModel2.setBubbleGradients(true);
        bubbleModel2.setBubbleAlpha(0.8);
        bubbleModel2.getAxis(AxisType.X).setTickAngle(-50);
        yAxis = bubbleModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setTickAngle(50);
    }
     
    private BubbleChartModel initBubbleModel(){
        BubbleChartModel model = new BubbleChartModel();
        lines=stationLineManagementLocal.findAllLines();
        
         for (Line l:lines)
         {
        	 int n=stationLineManagementLocal.findAllStationsByLineId(l.getLineId()).size();
        model.add(new BubbleChartSeries(l.getName(),l.getLineId(),n,50));
         }
         
        return model;
    }
 
}