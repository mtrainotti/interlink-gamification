package eu.fbk.interlink.gamification.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.fbk.interlink.gamification.domain.InterlinkGame;
import eu.fbk.interlink.gamification.domain.InterlinkGameTemplate;
import eu.fbk.interlink.gamification.repository.GameTemplateRepository;


@Component
public class GameTemlateComponent {
	
	@Autowired
    private GameTemplateRepository gameTemplateRepository;


	public List<InterlinkGameTemplate> findAll() {
		return this.gameTemplateRepository.findAll();
		
	}

	
	public Optional<InterlinkGameTemplate> findById(String id) {
		return this.gameTemplateRepository.findById(id);
		
	}

	
	public List<InterlinkGameTemplate> findByTags(List<String> tagList) {
		return this.gameTemplateRepository.findByTagList(tagList);
		
	}

	public InterlinkGame saveOrUpdateGame(InterlinkGameTemplate gametemplate) {
		return this.gameTemplateRepository.save(gametemplate);
		
	}
	//GameTemplate saveOrUpdateGame(Game game);

	//void deleteGameById(String id) 

}
