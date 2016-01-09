package sga.dominio.relatorios;

import java.awt.Desktop;
import java.io.File;
import java.util.logging.Level;
 



import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;

public class GeradorRelatorio {
	
	public void gerarRelatorio(String titulo) throws EngineException {
		 
	    IReportEngine engine = null;
	    EngineConfig config = null;
	 
	    try {
	    	//Configura e cria a Engine
	        config = new EngineConfig();          
	        config.setBIRTHome("C:\\birt\\birt-runtime-2_2_1\\birt-runtime-2_2_1\\ReportEngine");
	        config.setLogConfig("c:/temp/test", Level.FINEST);
	        Platform.startup(config);
	        final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
	            .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
	        engine = FACTORY.createReportEngine(config);       
	 
	        // Abre o ReportDesign
	        IReportRunnable design = null;
	        design = engine.openReportDesign("Reports/"+titulo+".rptdesign"); 
	        IRunAndRenderTask task = engine.createRunAndRenderTask(design);   
	        
	        //Configura as opções de renderização de PDF
	         final PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
	         //seta o nome e destino do arquivo criado
	         PDF_OPTIONS.setOutputFileName("c:/trabalho/"+titulo+".pdf");
	         //especifica formato
	         PDF_OPTIONS.setOutputFormat("pdf");
	         
	        //Gera o arquivo PDF
	        task.setRenderOption(PDF_OPTIONS);
	        task.run();
	        task.close();
	        engine.destroy();
	        //Abre o Arquivo PDF
	        File arquivo = new File("c:/trabalho/"+titulo+".pdf"); 
	        Desktop.getDesktop().open(arquivo); 
	    } catch(final Exception EX) {
	        EX.printStackTrace();
	    } finally {
	       Platform.shutdown();
	    }
	}
}
