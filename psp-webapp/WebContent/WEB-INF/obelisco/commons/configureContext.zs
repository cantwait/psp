import com.obelisco.comunes.*;
import com.pdvsa.psp.model.*;
import com.obelisco.modelo.data.seguridad.*;
import com.obelisco.modelo.servicios.seguridad.*;
import com.obelisco.vista.zk.command.*;
import org.zkoss.zk.ui.event.*;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletContext;
import org.zkoss.zkplus.spring.*;
import org.zkoss.util.media.*;
import org.zkoss.image.*;
import org.zkforge.timeline.*;
import org.zkforge.timeline.data.*;
import  org.zkoss.zkmax.ui.eq.*;
import org.zkoss.gmaps.*;
import com.obelisco.vista.zk.controls.*;
import com.obelisco.vista.zk.components.*;
import com.obelisco.vista.zk.util.OccurEventUtil;
import com.obelisco.modelo.*;
import java.util.*;
import java.text.*;
import org.springframework.security.*;



Session session = Sessions.getCurrent();



ContextoObelisco obeCtx = (ContextoObelisco) session.getAttribute(ContextoObelisco.ID_CONTEXTO_OBELISCO);


if (obeCtx != null) {
	usuario = obeCtx.getUsuarioActual();
}  else {

			obeCtx = new ContextoObeliscoImpl();

			//Usuario usuario = app.getGuestUser();;
			//obeCtx.setUsuarioActual(usuario);
			
			session.setAttribute(ContextoObelisco.ID_CONTEXTO_OBELISCO, obeCtx);

}




